/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.hawk.com.mybasicappcomponents.jni;

// Wrapper for native library

// Hawk: For non-NDK installed build env, I remarked the following code.
/*
public class AccelerometerGraphJNI {

     static {
         System.loadLibrary("accelerometergraph");
     }

     public static native void init(AssetManager assetManager);
     public static native void surfaceCreated();
     public static native void surfaceChanged(int width, int height);
     public static native void drawFrame();
     public static native void pause();
     public static native void resume();
}
*/