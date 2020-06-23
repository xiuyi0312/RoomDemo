package com.op.roomdemo.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.op.roomdemo.R;
import com.op.roomdemo.databinding.LayoutAlertDialogBinding;

public class AlertDialogFragment extends DialogFragment {

    private View.OnClickListener positiveClickListener;
    private View.OnClickListener negativeClickListener;
    private AlertDialogBean alertDialogBean;
    private LayoutAlertDialogBinding binding;

    private static AlertDialogFragment newInstance(AlertDialogBean dialogBean) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("dialog", dialogBean);
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }

    public void setPositiveClickListener(View.OnClickListener listener) {
        this.positiveClickListener = listener;
    }

    public void setNegativeClickListener(View.OnClickListener listener) {
        this.negativeClickListener = listener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        alertDialogBean = getArguments().getParcelable("dialog");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_alert_dialog, container, false);
        binding.setDialog(alertDialogBean);
        setCancelable(alertDialogBean.isCancelable());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                // FIXME: 2020/6/23 由于横竖屏切换后点击事件为空，因此切换后点击后无响应
                if(positiveClickListener != null) {
                    positiveClickListener.onClick(v);
                }
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(negativeClickListener != null) {
                    negativeClickListener.onClick(v);
                }
            }
        });
    }

    public static class Builder {

        private AlertDialogBean dialogBean;
        private View.OnClickListener positiveClickListener;
        private View.OnClickListener negativeClickListener;

        public Builder() {
            dialogBean = new AlertDialogBean();
        }

        public Builder setTitle(String title) {
            dialogBean.setTitle(title);
            return this;
        }

        public Builder setContent(String content) {
            dialogBean.setContent(content);
            return this;
        }

        public Builder setPositiveVisible(boolean visible) {
            dialogBean.setPositiveVisible(visible);
            return this;
        }

        public Builder setNegativeVisible(boolean visible) {
            dialogBean.setNegativeVisible(visible);
            return this;
        }

        public Builder setPositiveText(String text) {
            dialogBean.setPositiveText(text);
            return this;
        }

        public Builder setNegativeText(String text) {
            dialogBean.setNegativeText(text);
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            dialogBean.setCancelable(cancelable);
            return this;
        }

        public Builder setPositiveClickListener(View.OnClickListener listener) {
            this.positiveClickListener = listener;
            return this;
        }

        public Builder setNegativeClickListener(View.OnClickListener listener) {
            this.negativeClickListener = listener;
            return this;
        }

        public AlertDialogFragment build() {
            AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance(dialogBean);
            if (positiveClickListener != null) {
                alertDialogFragment.setPositiveClickListener(positiveClickListener);
            }
            if (negativeClickListener != null) {
                alertDialogFragment.setNegativeClickListener(negativeClickListener);
            }
            return alertDialogFragment;
        }
    }
}
