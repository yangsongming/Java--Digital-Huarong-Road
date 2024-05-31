package com.example.nr.slice;

import com.example.nr.ResourceTable;
import com.example.nr.conpo.TestPageProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.StateElement;
import ohos.agp.window.dialog.CommonDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import static com.example.nr.slice.PtSlice.*;
import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_CONTENT; // 注意引入

//import static com.example.nr.slice.test.albumImage;

import java.util.ArrayList;

public class PrechooseSlice1 extends AbilitySlice implements Component.ClickedListener {
    static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP,0,"SelectSlice: ");
    private Button btn_back, btn_play,quesbtn,btn_num;
    //private int isShowNum = 1; //是否显示数字
    private int diff = 3, diffNewVal;  // model=0 jig  model=1 huarong
    private int jigsawId = ResourceTable.Media_pic2;
    public static PageSlider pageSlider;
    private Button selectDiffBtn = null, selectModelBtn = null, selectFromAlbumBtn = null;
    private Picker diffPicker;
    private int pm_px, pg_px;
    //private boolean isShowNum = true;
    private Text diffText, displayText;
    private int RequestCode = 1234;
    private final int imgRequestCode = 1123;
    private Image albumImage = null;
    private boolean isSelectFromAlbum = false;
    private int imageWidth, imageHeight;

    private int[] images = {
            ResourceTable.Media_pic2,
            ResourceTable.Media_pic7,
            ResourceTable.Media_pic3,
            ResourceTable.Media_pic4,
            ResourceTable.Media_pic5,
            ResourceTable.Media_pic6,
            ResourceTable.Media_pic1
    };

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_preselect1_slice);

        requestPermissionsFromUser(new String[]{"ohos.permission.READ_USER_STORAGE","ohos.permission.CAMERA"},RequestCode);

        pm_px=AttrHelper.vp2px(getContext().getResourceManager().getDeviceCapability().width,this);
        pg_px=AttrHelper.vp2px(getContext().getResourceManager().getDeviceCapability().height,this);

        // 初始化组件
        initCom();

        // 添加响应事件
        addListener();

        albumImage.setPixelMap(ResourceTable.Media_pic7);

    }



    private void selectPic() {
        Intent intent = new Intent();
        Operation opt = new Intent.OperationBuilder().withAction("android.intent.action.GET_CONTENT").build();
        intent.setOperation(opt);
        intent.addFlags(Intent.FLAG_NOT_OHOS_COMPONENT);
        intent.setType("image/*");
        startAbilityForResult(intent, imgRequestCode);
    }


    /**
     * 添加响应事件
     */
    private void addListener(){
        btn_num.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                CommonDialog cd = new CommonDialog(getContext());
                cd.setCornerRadius(50);

                DirectionalLayout dl = (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_display, null, false);

                Button yes_btn = (Button) dl.findComponentById(ResourceTable.Id_yes_btn);
                yes_btn.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        PtSlice.isShowNum = 1 ;
                        String str ="您是否选择了提示"+ " 是";
                        displayText.setText(str);
                        cd.destroy();
                    }
                });

                Button no_btn = (Button) dl.findComponentById(ResourceTable.Id_no_btn);
                no_btn.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        PtSlice.isShowNum = 0 ;
                        String str ="您是否选择了提示"+ " 否";
                        displayText.setText(str);
                        cd.destroy();
                    }
                });

                cd.setSize(800, MATCH_CONTENT);
                cd.setContentCustomComponent(dl);
                cd.show();


            }
        });
        // 图片选择框
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int itemPos, float itemPosOffset, int itemPosPixles) {
            }
            @Override
            public void onPageSlideStateChanged(int state) {
            }
            @Override
            public void onPageChosen(int itemPos) {
                HiLog.info(label, "onPageChosen-itemPos=" + itemPos);
                jigsawId = images[itemPos];
            }
        });

        // 选择游戏难度tDialog
        selectDiffBtn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                CommonDialog cd = new CommonDialog(getContext());
                cd.setCornerRadius(50);
                DirectionalLayout dl = (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_diff_dialog, null, false);
                diffPicker = (Picker) dl.findComponentById(ResourceTable.Id_diff_picker);

                diffPicker.setMinValue(0); // 设置选择器中的最小值
                diffPicker.setMaxValue(6); // 设置选择器中的最大值
                diffPicker.setWheelModeEnabled(true);
                diffPicker.setValue(1);

                diffPicker.setFormatter(i -> {
                    String value;
                    switch (i) {
                        case 0:
                            value = "2×2";
                            break;
                        case 1:
                            value = "3×3";
                            break;
                        case 2:
                            value = "4×4";
                            break;
                        case 3:
                            value = "5×5";
                            break;
                        case 4:
                            value = "6×6";
                            break;
                        case 5:
                            value = "7×7";
                            break;
                        case 6:
                            value = "8×8";
                            break;
                        default:
                            value = "" + i;
                    }
                    return value;
                });

                diffPicker.setValueChangedListener((picker1, oldVal, newVal) -> {
                    // oldVal:上一次选择的值； newVal：最新选择的值
                    HiLog.info(label, "picker_newVal=" + newVal);
                    diffNewVal = newVal +2;
                });


                Button btn_cancel = (Button) dl.findComponentById(ResourceTable.Id_diff_cancel);
                btn_cancel.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        cd.destroy();
                    }
                });



                Button btn_ok = (Button) dl.findComponentById(ResourceTable.Id_diff_ok);
                btn_ok.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        String str ="您选择的难度为"+ diffNewVal+"×"+diffNewVal;
                        diffText.setText(str);
                        diff = diffNewVal;
                        cd.destroy();
                    }
                });
                cd.setSize(800, MATCH_CONTENT);
                cd.setContentCustomComponent(dl);
                cd.show();
            }
        });


    }


    private void resetAll(){
        diff = 3;
        //model = 0;
        diffText = (Text) findComponentById(ResourceTable.Id_diffText);
        diffText.setText("您选择的难度为 3×3");
        displayText = (Text) findComponentById(ResourceTable.Id_displayText);
        displayText.setText("您是否选择了提示 是");

        albumImage.setWidth(0);
        albumImage.setHeight(0);
        pageSlider.setWidth(imageWidth);
        pageSlider.setHeight(imageHeight);
        isSelectFromAlbum = false;
    }

    /**
     * 初始化组件
     */
    private void initCom() {
        btn_play = (Button) findComponentById(ResourceTable.Id_btn_play);
        btn_back = (Button) findComponentById(ResourceTable.Id_btn_back);
        quesbtn = (Button) findComponentById(ResourceTable.Id_quesbnt);
        btn_num = (Button) findComponentById(ResourceTable.Id_btn_num);
        btn_play.setClickedListener(this);
        btn_back.setClickedListener(this);
        quesbtn.setClickedListener(this);
        btn_num.setClickedListener(this);


        initPageSlider();

        selectDiffBtn = (Button) findComponentById(ResourceTable.Id_selectDiffBtn);
        diffText = (Text) findComponentById(ResourceTable.Id_diffText);
        displayText = (Text) findComponentById(ResourceTable.Id_displayText);


        imageWidth=  pageSlider.getWidth();
        imageHeight = pageSlider.getHeight();
        albumImage = (Image) findComponentById(ResourceTable.Id_albumImage);
        albumImage.setWidth(0);
        albumImage.setHeight(0);
    }

    /**
     * 初始化pageSilder
     */
    private void initPageSlider() {
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_page_slider);
        pageSlider.setProvider(new TestPageProvider(getData(), this));
        pageSlider.setReboundEffect(true);
    }

    /**
     * 添加pageSlider页面
     * @return
     */
    private ArrayList<TestPageProvider.DataItem> getData() {
        ArrayList<TestPageProvider.DataItem> dataItems = new ArrayList<>();
        int len = images.length;
        for(int i = 0; i<len; i++){
            dataItems.add(new TestPageProvider.DataItem(images[i]));
        }
        return dataItems;
    }


    /**
     * 获取radioButton的样式
     * @return
     */
    private StateElement createStateElement() {
        ShapeElement elementButtonOn = new ShapeElement();
        elementButtonOn.setRgbColor(RgbPalette.RED);
        elementButtonOn.setShape(ShapeElement.OVAL);

        ShapeElement elementButtonOff = new ShapeElement();
        elementButtonOff.setRgbColor(RgbPalette.WHITE);
        elementButtonOff.setShape(ShapeElement.OVAL);

        StateElement checkElement = new StateElement();
        checkElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, elementButtonOn);
        checkElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, elementButtonOff);
        return checkElement;
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
        if (component == btn_back) {
            AbilitySlice slice = new MainAbilitySlice();
            Intent intent = new Intent();
            present(slice, intent);

        }
        else if (component == quesbtn) {
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
        else if (component == btn_play) {
            AbilitySlice slice = new PtSlice();
            Intent intent = new Intent();
            intent.setParam("jigsawId", jigsawId);
            intent.setParam("diff", diff);
            intent.setParam("isShowNum", isShowNum);
            present(slice, intent);
            resetAll();

        }
    }
}
