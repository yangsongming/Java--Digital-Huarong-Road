package com.example.nr.slice;

import com.example.nr.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private Button btn_about, btn_guide, btn_play, btn_play2,test,cg;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initCom();
    }

    /**
     * 初始化组件
     */
    private void initCom(){
        btn_about = (Button) findComponentById(ResourceTable.Id_btn_about);
        btn_play = (Button) findComponentById(ResourceTable.Id_btn_play);
        btn_play2 = (Button) findComponentById(ResourceTable.Id_btn_play2);
        btn_guide = (Button) findComponentById(ResourceTable.Id_btn_guide);
        //test = (Button) findComponentById(ResourceTable.Id_test);
        cg = (Button) findComponentById(ResourceTable.Id_cg);

        btn_about.setClickedListener(this);
        btn_play.setClickedListener(this);
        btn_play2.setClickedListener(this);
        btn_guide.setClickedListener(this);
        //test.setClickedListener(this);
        cg.setClickedListener(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        AbilitySlice slice = null;
        if(component == btn_about){
            slice = new AboutMeSlice();
        }
        else if(component == btn_play2){
            slice = new PrechooseSlice1();
        }
        else if(component == btn_guide){
            slice = new GuideSlice();
        }
        else if(component == btn_play){
            slice = new PrechooseSlice();
        }
//        else if(component == test){
//            slice = new xzSlice();
//        }
        else if(component == cg){
            slice = new CgSlice();
        }

        Intent intent = new Intent();
        present(slice, intent);

    }
}
