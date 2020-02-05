// IAppAIDL.aidl
package sample.hawk.com.mybasicappcomponents.ipc.aidls;

import sample.hawk.com.mybasicappcomponents.ipc.aidls.ISvcAIDL;
// Declare any non-default types here with import statements

// AIDL_1、創建AIDL檔, 在這個檔裡面定義介面, 該介面定義了可供用戶端訪問的方法和屬性。 如: IAppAIDL.adil
// AIDL 2、編譯AIDL檔,使用Eclipse plugin的話,可以根據adil檔自動生產java檔並編譯.
//         注意: 理論上, 參數可以傳遞基底資料型別和String, 還有就是Bundle的派生類, 不過在Eclipse中,目前的ADT不支援Bundle做為參數.

// AIDL的創建方法:
// AIDL語法很簡單,可以用來聲明一個帶一個或多個方法的介面，也可以傳遞參數和返回值。 由於遠端調用的需要, 這些參數和返回值並不是任何類型.
// 下面是些AIDL支持的資料類型:
// 1. 不需要import聲明的簡單Java程式設計語言類型(int,boolean等)
// 2. String, CharSequence不需要特殊聲明
// 3. List, Map和Parcelables類型, 這些類型內所包含的資料成員也只能是單一資料型別, String等其他比支持的類型.
interface IAppAIDL {
    void AppAPI(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);
}
