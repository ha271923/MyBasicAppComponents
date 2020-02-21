package sample.hawk.com.mybasicappcomponents.data_structure.protobuf;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.protobuf.InvalidProtocolBufferException;
import com.mohsenoid.protobuftest.AddressBookProtos;

import kotlin.collections.CollectionsKt;
import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MyProtobufActivity extends Activity {
    private static final String SAMPLE_JSON = "{\n" +
            "  \"addressbook\": [\n" +
            "    {\n" +
            "      \"person\": {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Mohsen\",\n" +
            "        \"email\": \"info@mohsenoid.com\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"phones\": [\n" +
            "        {\n" +
            "          \"phone\": {\n" +
            "            \"number\": \"+49123456\",\n" +
            "            \"type\": \"HOME\"\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"phone\": {\n" +
            "            \"number\": \"+49654321\",\n" +
            "            \"type\": \"MOBILE\"\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprotobuf_activity);
    }


    public void onClick_createAddressBook(View view) {
        AddressBookProtos.AddressBook addressBook = createAddressBookProtobuf();

        // Serialize AddressBook to ByteArray
        byte[]  bytes = addressBook.toByteArray();

        // Deserialize AddressBook ByteArray
        AddressBookProtos.AddressBook myAddressBook = null;
        try {
            myAddressBook = AddressBookProtos.AddressBook.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        SMLog.i(myAddressBook.toString());

        SMLog.i("Protobuf byte size: ${bytes.size}");
        SMLog.i("JSON byte size: ${SAMPLE_JSON.toByteArray().size}");
    }

    private static AddressBookProtos.AddressBook createAddressBookProtobuf() {
        // building PhoneNumber objects
        AddressBookProtos.Person.PhoneNumber phoneHome = AddressBookProtos.Person.PhoneNumber.newBuilder()
                .setNumber("+49123456")
                .setType(AddressBookProtos.Person.PhoneType.HOME)
                .build();
        AddressBookProtos.Person.PhoneNumber phoneMobile = AddressBookProtos.Person.PhoneNumber.newBuilder()
                .setNumber("+49654321")
                .setType(AddressBookProtos.Person.PhoneType.MOBILE)
                .build();

        // building a Person object using phone data
        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder()
                .setId(1)
                .setName("Mohsen")
                .setEmail("info@mohsenoid.com")
                .addAllPhones( (Iterable)CollectionsKt.listOf(phoneHome, phoneMobile))
                .build();

        // building an AddressBook object using person data
        AddressBookProtos.AddressBook addressBook =  AddressBookProtos.AddressBook.newBuilder()
                .addAllPeople( (Iterable)CollectionsKt.listOf((person)))
                .build();

        return addressBook;
    }

    public void onClick_addPeople(View view) {
        sendRequest();
    }
    private void sendRequest() {
        AddressBookProtos.Person.Builder person = AddressBookProtos.Person.newBuilder().
                setId(1).
                setEmail("we@ff.com").
                setName("wikiddd");
        AddressBookProtos.Person.PhoneNumber.Builder phoneNumber = AddressBookProtos.Person.PhoneNumber.newBuilder().
                setType(AddressBookProtos.Person.PhoneType.MOBILE).
                setNumber("234235634634");
        person.addPhones(phoneNumber);

        AddressBookProtos.AddressBook.Builder addressBook = AddressBookProtos.AddressBook.newBuilder().
                addPeople(person);

        SocketHandler.send(addressBook.build().toByteArray());
    }


}