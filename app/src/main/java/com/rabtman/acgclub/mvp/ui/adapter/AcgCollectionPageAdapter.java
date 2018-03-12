package com.rabtman.acgclub.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.rabtman.acgclub.base.constant.SystemConstant;
import java.util.List;

/**
 * @author zjm
 * @Description:
 * @date 2016/11/23
 */

public class AcgCollectionPageAdapter extends FragmentPagerAdapter {

  private List<Fragment> fragments;

  public AcgCollectionPageAdapter(FragmentManager fragmentManager,
      List<Fragment> fragments) {
    super(fragmentManager);
    this.fragments = fragments;
  }

  @Override
  public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override
  public int getCount() {
    return fragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return SystemConstant.ACG_COLLECTION_TITLE.get(position);
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    //super.destroyItem(container, position, object);
  }
}
