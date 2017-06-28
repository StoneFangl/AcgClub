package com.rabtman.common.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hss01248.dialog.StyledDialog;
import com.rabtman.common.base.mvp.IView;
import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by codeest on 16/8/11.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SupportActivity implements
    IView {

  protected BaseApplication mApplication;
  protected Activity mContext;
  private Dialog mLoadingDialog;
  private Unbinder mUnBinder;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    mUnBinder = ButterKnife.bind(this);
    mApplication = (BaseApplication) getApplication();
    mContext = this;
    onViewCreated();
    initData();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mUnBinder != Unbinder.EMPTY) {
      mUnBinder.unbind();
    }
    this.mUnBinder = null;
    this.mApplication = null;
  }

  protected void setToolBar(Toolbar toolbar, String title) {
    toolbar.setTitle(title);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressedSupport();
      }
    });
  }

  @Override
  public void showLoading() {
    mLoadingDialog = StyledDialog.buildMdLoading().show();
  }

  @Override
  public void hideLoading() {
    if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
      StyledDialog.dismiss(mLoadingDialog);
    }
  }

  @Override
  public void showMsg(int stringId) {
    showMsg(getString(stringId));
  }

  @Override
  public void showMsg(String message) {
    Toasty.info(mContext, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showError(int stringId) {
    showError(getString(stringId));
  }

  @Override
  public void showError(String message) {
    hideLoading();
    Toasty.error(mContext, message, Toast.LENGTH_SHORT).show();
  }

  protected void onViewCreated() {

  }

  protected abstract int getLayoutId();

  protected abstract void initData();
}
