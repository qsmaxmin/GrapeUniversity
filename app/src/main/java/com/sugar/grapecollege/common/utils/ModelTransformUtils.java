package com.sugar.grapecollege.common.utils;

import com.sugar.grapecollege.common.model.ModelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/8 14:32
 * @Description 将一个model集合转换成一个ModelAdapter[]集合，或者转回来
 * 模型的相互转换，目的是转换成适合列表展示的model
 * List<{@link ModelAdapter<>[]}> 转换成 List<T>
 * List<T> 转换成 List<{@link ModelAdapter[]}>
 */

public class ModelTransformUtils {

    /**
     * 模型转换
     */
    public static <T> ModelAdapter<T> transform(T t) {
        ModelAdapter<T> modelAdapter = new ModelAdapter<>();
        modelAdapter.model = t;
        return modelAdapter;
    }

    /**
     * 模型集合转换
     */
    public static <T> List<ModelAdapter<T>> transform(List<T> list) {
        ArrayList<ModelAdapter<T>> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (int i = 0, size = list.size(); i < size; i++) {
                result.add(transform(list.get(i)));
            }
        }
        return result;
    }

    /**
     * 模型转换，转换成适合列表展示的模型
     *
     * @param list        原始集合
     * @param arrayLength 生成的数组长度
     */
    public static <T> List<ModelAdapter<T>[]> transform(List<T> list, int arrayLength) {
        ArrayList<ModelAdapter<T>[]> result = new ArrayList<>();
        if (list != null && !list.isEmpty() && arrayLength > 0) {
            ModelAdapter<T>[] fontArr = null;
            for (int i = 0, size = list.size(); i < size; i++) {
                T t = list.get(i);
                if (i % arrayLength == 0) {
                    fontArr = new ModelAdapter[(i == size - 1) ? 1 : arrayLength];
                    fontArr[0] = transform(t);
                    result.add(fontArr);
                } else {
                    if (fontArr != null) fontArr[i % arrayLength] = transform(t);
                }
            }
        }
        return result;
    }

    /**
     * 模型转换
     */
    public static <T> T transformBack(ModelAdapter<T> adapter) {
        if (adapter != null) {
            return adapter.model;
        }
        return null;
    }

    /**
     * 模型集合转换，转换回来
     */
    public static <T> List<T> transformBack(List<ModelAdapter<T>> list) {
        ArrayList<T> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (int i = 0, size = list.size(); i < size; i++) {
                ModelAdapter<T> adapter = list.get(i);
                if (adapter != null) result.add(adapter.model);
            }
        }
        return result;
    }

    /**
     * 模型集合转换，转换回来
     */
    public static <T> List<T> transformBacks(List<ModelAdapter<T>[]> list) {
        ArrayList<T> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (ModelAdapter<T>[] adapters : list) {
                if (adapters != null) {
                    for (ModelAdapter<T> model : adapters) {
                        if (model != null) result.add(model.model);
                    }
                }
            }
        }
        return result;
    }
}
