package com.example.nr.slice;

import com.example.nr.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;



/* 这段代码主要用于相关的app的测试 */




public class xzSlice extends AbilitySlice {
    private Button btn_back,hrguide,ptguide;
    private Image img_tip1;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_examine);



        btn_back = (Button) findComponentById(ResourceTable.Id_btn_back);
        btn_back.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                AbilitySlice slice = new MainAbilitySlice();
                Intent intent = new Intent();
                present(slice, intent);
            }
        });

    }
//    private void addListener(){
//        //if(Hrcg1Slice.flag1==1){
//            this.img_tip1 = (Image) findComponentById(ResourceTable.Id_test);
//            img_tip1.setPixelMap(HrSlice.jigsawPixelMap);
//        //}
//    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}