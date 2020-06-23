package com.op.roomdemo.widget;

import android.os.Parcel;
import android.os.Parcelable;

public class AlertDialogBean implements Parcelable {
    private String positiveText = "确定";
    private String negativeText = "取消";
    private boolean positiveVisible = true;
    private boolean negativeVisible = true;
    private String title;
    private String content;
    private boolean cancelable = true;

    public AlertDialogBean() {

    }

    protected AlertDialogBean(Parcel in) {
        positiveText = in.readString();
        negativeText = in.readString();
        positiveVisible = in.readByte() != 0;
        negativeVisible = in.readByte() != 0;
        title = in.readString();
        content = in.readString();
        cancelable = in.readByte() != 0;
    }

    public static final Creator<AlertDialogBean> CREATOR = new Creator<AlertDialogBean>() {
        @Override
        public AlertDialogBean createFromParcel(Parcel in) {
            return new AlertDialogBean(in);
        }

        @Override
        public AlertDialogBean[] newArray(int size) {
            return new AlertDialogBean[size];
        }
    };

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public boolean isPositiveVisible() {
        return positiveVisible;
    }

    public void setPositiveVisible(boolean positiveVisible) {
        this.positiveVisible = positiveVisible;
    }

    public boolean isNegativeVisible() {
        return negativeVisible;
    }

    public void setNegativeVisible(boolean negativeVisible) {
        this.negativeVisible = negativeVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(positiveText);
        dest.writeString(negativeText);
        dest.writeByte((byte) (positiveVisible ? 1 : 0));
        dest.writeByte((byte) (negativeVisible ? 1 : 0));
        dest.writeString(title);
        dest.writeString(content);
        dest.writeByte((byte) (cancelable ? 1 : 0));
    }
}
