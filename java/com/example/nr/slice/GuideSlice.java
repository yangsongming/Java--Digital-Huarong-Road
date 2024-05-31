package com.example.nr.slice;

import com.example.nr.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.window.dialog.CommonDialog;

import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_CONTENT;

public class GuideSlice extends AbilitySlice {
    private Button btn_back,hrguide,ptguide,cgguide;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_guide_slice);


        btn_back = (Button) findComponentById(ResourceTable.Id_btn_back);
        btn_back.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                AbilitySlice slice = new MainAbilitySlice();
                Intent intent = new Intent();
                present(slice,intent);
            }
        });

        hrguide = (Button) findComponentById(ResourceTable.Id_hrguide);
        hrguide.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                CommonDialog cd = new CommonDialog(getContext());
                cd.setCornerRadius(50);

                DirectionalLayout dl = (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_guide2, null, false);

                Button ok_btn = (Button) dl.findComponentById(ResourceTable.Id_ok_btn);
                ok_btn.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        cd.destroy();
                    }
                });

                cd.setSize(800, MATCH_CONTENT);
                cd.setContentCustomComponent(dl);
                cd.show();
            }
        });

        ptguide = (Button) findComponentById(ResourceTable.Id_ptguide);
        ptguide.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                CommonDialog cd = new CommonDialog(getContext());
                cd.setCornerRadius(50);

                DirectionalLayout dl = (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_guide1, null, false);

                Button ok_btn = (Button) dl.findComponentById(ResourceTable.Id_ok_btn);
                ok_btn.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {

                        cd.destroy();
                    }
                });

                cd.setSize(800, MATCH_CONTENT);
                cd.setContentCustomComponent(dl);
                cd.show();
            }
        });
        cgguide = (Button) findComponentById(ResourceTable.Id_cgguide);
        cgguide.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                CommonDialog cd = new CommonDialog(getContext());
                cd.setCornerRadius(50);

                DirectionalLayout dl = (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_guide3, null, false);

                Button ok_btn = (Button) dl.findComponentById(ResourceTable.Id_ok_btn);
                ok_btn.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        cd.destroy();
                    }
                });

                cd.setSize(800, MATCH_CONTENT);
                cd.setContentCustomComponent(dl);
                cd.show();
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}