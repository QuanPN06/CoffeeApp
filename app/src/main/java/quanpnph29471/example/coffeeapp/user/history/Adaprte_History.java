package quanpnph29471.example.coffeeapp.user.history;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Adaprte_History extends FragmentStateAdapter {


    public Adaprte_History(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChoXN_Fragment();
            case 1:
                return new TrangThai_Fragment();
            case 2:
                return new DaNhan_Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
