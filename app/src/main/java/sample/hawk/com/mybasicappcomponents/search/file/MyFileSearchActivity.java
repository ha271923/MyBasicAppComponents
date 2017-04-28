package sample.hawk.com.mybasicappcomponents.search.file;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.R;

public class MyFileSearchActivity extends ListActivity {
    private static final String TAG = "MyFileSearchActivity";

    private static final String SEARCH_THIS_FILE_EXTENSION ="gif";
    public static final String EXTRA_SELECTED_FILE = "selectedFile";
    private ArrayAdapter adapter;
    private Handler handler;
    private final List<File> listItems;
    private int previewPadding;
    private int previewWidth;
    private SearchThread searchThread;

    class SearchResultItem extends ArrayAdapter<File> { // Hawk: I rename it as SearchResultItem from ID class.
        SearchResultItem(Context context, int res_id, List data) {
            super(context, res_id, data);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = MyFileSearchActivity.this.getLayoutInflater().inflate(android.R.layout.simple_list_item_2, parent, false);
            view.setBackgroundColor(Color.LTGRAY);
            File file = (File) MyFileSearchActivity.this.listItems.get(position);
            ((TextView) view.findViewById(android.R.id.text1)).setText(file.getName());
            if (file.getParentFile() != null) {
                ((TextView) view.findViewById(android.R.id.text2)).setText(file.getParentFile().getAbsolutePath());
            }
            Bitmap bm = null;
            try {
                bm = BitmapFactory.decodeFile(file.getPath());
                if (bm == null) {
                }
                if (bm.getWidth() != MyFileSearchActivity.this.previewWidth) {
                    Bitmap old = bm;
                    bm = Bitmap.createScaledBitmap(bm, MyFileSearchActivity.this.previewWidth, (MyFileSearchActivity.this.previewWidth * bm.getHeight()) / bm.getWidth(), false);
                    old.recycle();
                }
            } catch (Throwable e) {
                if (bm != null) {
                    bm.recycle();
                }
                if (bm != null) {
                    ((TextView) view.findViewById(android.R.id.text1)).setCompoundDrawablePadding(MyFileSearchActivity.this.previewPadding);
                    ((TextView) view.findViewById(android.R.id.text1)).setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(MyFileSearchActivity.this.getResources(), bm), null, null, null);
                }
                return view;
            }
            /*
            // Hawk: remark this catch, since it's always showing this exception has been caught.
            catch (NullPointerException e2) {
                NullPointerException nullPointerException = e2;
                if (bm != null) {
                    bm.recycle();
                }
                if (bm != null) {
                    ((TextView) view.findViewById(android.R.id.text1)).setCompoundDrawablePadding(MyFileSearchActivity.this.previewPadding);
                    ((TextView) view.findViewById(android.R.id.text1)).setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(MyFileSearchActivity.this.getResources(), bm), null, null, null);
                }
                return view;
            }
            */
            if (bm != null) {
                ((TextView) view.findViewById(android.R.id.text1)).setCompoundDrawablePadding(MyFileSearchActivity.this.previewPadding);
                ((TextView) view.findViewById(android.R.id.text1)).setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(MyFileSearchActivity.this.getResources(), bm), null, null, null);
            }
            return view;
        }
    }

    private class ExtensionFilter implements FileFilter {
        private final String extension;

        public ExtensionFilter(String extension) {
            this.extension = extension;
        }

        public boolean accept(File file) {
            return file.getName().toLowerCase().endsWith(this.extension.toLowerCase());
        }
    }

    private class SearchThread extends Thread {
        final List<File> dirs;
        final FileFilter filter;
        boolean finished;

        class show_searchIndicator implements Runnable {
            show_searchIndicator() {
            }

            public void run() {
                MyFileSearchActivity.this.findViewById(R.id.searchIndicator).setVisibility(View.GONE);
            }
        }

        class show_searchNoMatches implements Runnable {
            show_searchNoMatches() {
            }

            public void run() {
                if (MyFileSearchActivity.this.listItems.size() == 0) {
                    MyFileSearchActivity.this.findViewById(R.id.searchNoMatches).setVisibility(View.VISIBLE);
                } else {
                    MyFileSearchActivity.this.findViewById(R.id.searchNoMatches).setVisibility(View.GONE);
                }
            }
        }

        class show_searchListItem implements Runnable {
            final File match_file;

            show_searchListItem(File match_file) {
                this.match_file = match_file;
            }

            public void run() {
                MyFileSearchActivity.this.listItems.add(this.match_file);
                MyFileSearchActivity.this.adapter.notifyDataSetChanged();
            }
        }

        public SearchThread(FileFilter filter) {
            this.finished = false;
            this.filter = filter;
            this.dirs = new LinkedList();
        }

        public void run() {
            this.dirs.add(Environment.getExternalStorageDirectory());
            this.dirs.add(Environment.getRootDirectory());
            while (this.dirs.size() > 0 && !this.finished) {
                listFiles((File) this.dirs.remove(0), this.filter);
            }
            MyFileSearchActivity.this.handler.post(new show_searchIndicator());
            MyFileSearchActivity.this.handler.post(new show_searchNoMatches());
        }

        public void finish() {
            this.finished = true;
        }

        void listFiles(File file, FileFilter filter) {
            int length;
            int i = 0;
            File[] matches = file.listFiles(filter);
            if (matches != null) {
                for (File match : matches) {
                    MyFileSearchActivity.this.handler.post(new show_searchListItem(match));
                }
            }
            File[] allFiles = file.listFiles();
            if (allFiles != null) {
                length = allFiles.length;
                while (i < length) {
                    File allFile = allFiles[i];
                    if (allFile.listFiles() != null) {
                        this.dirs.add(allFile);
                    }
                    i++;
                }
            }
        }
    }

    public MyFileSearchActivity() {
        this.listItems = new LinkedList();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources r = getResources();
        this.previewWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64.0f, r.getDisplayMetrics());
        this.previewPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, r.getDisplayMetrics());
        this.handler = new Handler();
        setContentView(R.layout.file_search_picker);
        this.adapter = new SearchResultItem(this, android.R.layout.simple_list_item_1, this.listItems);
        setListAdapter(this.adapter);
        this.searchThread = new SearchThread(new ExtensionFilter(SEARCH_THIS_FILE_EXTENSION));
        this.searchThread.start();
    }

    protected void onStop() {
        super.onStop();
        this.searchThread.finish();
        try {
            this.searchThread.join();
        } catch (InterruptedException e) {
        }
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent result = new Intent();
        result.putExtra(EXTRA_SELECTED_FILE, Uri.fromFile((File) this.listItems.get(position)));
        setResult(RESULT_OK, result);
        finish();
    }
}