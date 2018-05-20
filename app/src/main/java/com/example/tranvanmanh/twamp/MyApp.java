package com.example.tranvanmanh.twamp;

import android.app.Application;

/**
 * Created by nampham on 3/10/18.
 */

public class MyApp extends Application {

    public static String infoPacketLoss;
    public static String infoDupplicate;
    public static String infoOutOfOrder;
    public static String infoInterDelay;
    public static String infoMaxLatency;
    public static String infoMinLatency;
    public static String infoAvgLatency;
    public static String infoMaxIpdv;
    public static String infoMinIpdv;
    public static String infoAvgIpdv;
    public static String infoTotalPacket;


    @Override
    public void onCreate() {
        super.onCreate();

        infoPacketLoss = "Đo số lượng gói tin bị mất trong tổng số lượng gói tin được gửi đi, mất gói tin xảy ra khi bên nhận không nhậ được gói tin từ bên gửi \n Mất gói tin thường do lỗi trong quá trình truyền dữ liệu, thường là qua mạng không dây hoặc do tắc nghẽn mạng. \n Một số nguyên nhân phổ biến như do nhiễu tần số vô tuyến (RF interference), tín hiệu vô tuyến yếu do khoảng cách hoặc do có quá nhiều thiết bị kết nối, do router quá tải làm gói tin bị loại bỏ\n mất gói tin làm ảnh hưởng tói băng thông, nếu là truyền bằng các giao thức đáng tin cậy thì gói tin sẽ được gủi lại điều này sẽ làm tăng latency. \n mất gói tin bao nhiêu là chấp nhận được ? Lý tưởng là 0% tuy nhiên nó còn tùy thuộc vào mục đích sử dụng là gì: truyền file, gọi phone (voip), hay truyền video. Đối với truyền VIdeo thì mất gói tin trong khoảng 1-2.5% la chấp nhận được. Tuy nhiên đối với truyền data thì mất gói tin là không chấp nhận vì nó sẽ gây ra lỗi nghiêm trọng như truyền telnet, web(http,https), mail...";
        infoOutOfOrder = "Mỗi gói tin dược gắng một số thứ tự riêng biệt, và số thứ tự này sẽ tăng dần thường tăng lên một, nếu so sánh thứ tự bên nhận và bên gửi không trùng thì lúc này sẽ xảy ra Out Of Order Packet. ";
        infoDupplicate = "Trường hợp Duplicate packet hay trùng lặp gói tin xảy ra khi bên nhận nhận được nhiều hơn một gói tin có cùng chỉ số sequence number nghĩa là nhận được hai hay nhiều hơn số lượng gói tin bị trùng nhau.";
    }

    private static final String TAG = MyApp.class.getSimpleName();

}











































