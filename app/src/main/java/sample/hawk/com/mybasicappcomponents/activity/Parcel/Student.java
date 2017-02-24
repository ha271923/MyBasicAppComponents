package sample.hawk.com.mybasicappcomponents.activity.Parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 android提供了一種新的類型：Parcel。本類被用作封裝資料的容器，封裝後的資料可以通過Intent或IPC傳遞。 除了基本類型以外，只有實現了Parcelable介面的類才能被放入Parcel中。

 Parcelable實現要點：需要實現三個東西:

 1）writeToParcel(Parcel dest, int flags) 方法。該方法將類的資料寫入外部提供的Parcel中.
 2）describeContents方法。沒搞懂有什麼用，反正直接返回0也可以
 3）靜態的Parcelable.Creator介面，本介面有兩個方法：
    A. createFromParcel(Parcel in) 實現從in中創建出類的實例的功能;
    B. newArray(int size) 創建一個類型為T，長度為size的陣列，僅一句話（return new T[size])即可。估計本方法是供外部類反序列化本類陣列使用。

 */

public class Student implements Parcelable {
    public int number;
    public String name;
    public int sex;
    public int age;

    Student(){
    }

    Student (Parcel p){
        number = p.readInt();
        name = p.readString();
        sex = p.readInt();
        age = p.readInt();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel p) {
            return new Student(p);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel p, int flags) {
        p.writeInt(number);
        p.writeString(name);
        p.writeInt(sex);
        p.writeInt(age);
    }
}

