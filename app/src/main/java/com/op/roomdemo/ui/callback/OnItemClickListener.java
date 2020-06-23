package com.op.roomdemo.ui.callback;

public interface OnItemClickListener<T> {
    void onItemClick(T u);
    void onItemLongClick(T u);
}
