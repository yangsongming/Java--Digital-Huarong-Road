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

public class CgSlice extends AbilitySlice implements Component.ClickedListener {
    private Button btn_play, quesbnt,  btn_play2,btn_back;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_cg);
        initCom();
    }

    /**
     * 初始化组件
     */
    private void initCom(){
        btn_play = (Button) findComponentById(ResourceTable.Id_btn_play);
        btn_play2 = (Button) findComponentById(ResourceTable.Id_btn_play2);
        btn_back= (Button) findComponentById(ResourceTable.Id_btn_back);
        quesbnt= (Button) findComponentById(ResourceTable.Id_quesbnt);
        btn_play.setClickedListener(this);
        btn_play2.setClickedListener(this);
        btn_back.setClickedListener(this);
        quesbnt.setClickedListener(this);
    }

    void tip(){
        CommonDialog cd = new CommonDialog(getContext());
        cd.setCornerRadius(50);
        DirectionalLayout dl = (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_attention_dialog, null, false);

        Button ok = (Button) dl.findComponentById(ResourceTable.Id_ok_btn);
        ok.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                cd.destroy();
            }
        });
        cd.setSize(1000, MATCH_CONTENT);
        cd.setContentCustomComponent(dl);
        cd.show();
    }

    @Override
    public void onClick(Component component) {
        AbilitySlice slice = null;
        if(component == btn_play){
            slice = new PreCg1Slice();
            Intent intent = new Intent();
            present(slice, intent);
        }
        else if(component == btn_play2){     //设置关卡的串行逻辑
            if(Hrcg1Slice.flag==1) {
                slice = new PreCg2Slice();
                Intent intent = new Intent();
                present(slice, intent);
            }
            else{
                tip();
            }
        }
        else if(component == btn_back){
            slice = new MainAbilitySlice();
            Intent intent = new Intent();
            present(slice, intent);
        }
        else if(component == quesbnt){
            CommonDialog cd = new CommonDialog(getContext());
            cd.setCornerRadius(50);
            DirectionalLayout dl = (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_guide3, null, false);

            Button ok = (Button) dl.findComponentById(ResourceTable.Id_ok_btn);
            ok.setClickedListener(new Component.ClickedListener() {
                @Override
                public void onClick(Component component) {
                    cd.destroy();
                }
            });
            cd.setSize(1000, MATCH_CONTENT);
            cd.setContentCustomComponent(dl);
            cd.show();
        }
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
