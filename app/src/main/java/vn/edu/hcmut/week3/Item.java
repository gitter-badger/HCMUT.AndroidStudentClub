//package vn.edu.hcmut.week3;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//public class Item implements Parcelable {
//
//    public String id, name, email;
//
//    public Item(String id, String name, String email) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//    }
//
//    public Item(Parcel in) {
//        id = in.readString();
//        name = in.readString();
//        email = in.readString();
//    }
//
//    @Override
//    public int describeContents() {
//        return 3;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeString(name);
//        dest.writeString(email);
//    }
//
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public Item createFromParcel(Parcel in) {
//            return new Item(in);
//        }
//
//        public Item[] newArray(int size) {
//            return new Item[size];
//        }
//    };
//
//}
