package com.dksvip.core.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


@Accessors(chain = true)
@Data
public class CodeMealInfo {

    /**
     * 兑换码
     */
    private String vipCode;

    /**
     * 兑换码状态
     */
    private Integer codeStatus;


    /**
     * 可选方案 数量
     */
    private Integer selectionCount;


    /**
     * 可选方案
     */
    private List<MealSelection> selections;

    /**
     * 门店类型0=普通门店，1=溢价门店
     */
    private Integer storeType = 0;


    public void addMealSelection(MealSelection mealSelection) {
        if (selections == null) {
            selections = new ArrayList<>();
        }
        selections.add(mealSelection);
    }





    public void updateSelectionCount(){
        if(selections == null){
            this.selectionCount = 0;
        }else {
            this.selectionCount = selections.size();
        }
    }

}


