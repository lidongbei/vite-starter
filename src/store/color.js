import { defineStore } from "pinia";
import { darken, lighten } from "@/utils/color.js";

export const useColorStore = defineStore("color", () => {

  const _namespace = 'el'

  const setThemeColor = (colorMap) => {
    Object.keys(colorMap).forEach(key => {
      const v = colorMap[key]
      setPropertyColor(`--${_namespace}-color-${key}`, v);
      themeColorGradient(`--${_namespace}-color-${key}-light-#level#`, lighten, v);
      setPropertyColor(`--${_namespace}-color-${key}-dark-2`, v, darken);
    })
  }

  /**
   * 将css3变量设置到document中方便全局调用
   */
  const setPropertyColor = (varName, color, fun, level) => {
    level = level ? level : 0;
    fun = fun ? fun : lighten;
    document.documentElement.style.setProperty(
      varName,
      fun(color, level / 10)
    );
  }

  /**
   * 生成主色的其余渐变色并修改对应CSS3变量值
   */
  const themeColorGradient = (varName, fun, themeColor, themeLevel) => {
    themeColor = themeColor ? themeColor : '#409eff';
    themeLevel = themeLevel ? themeLevel : [3, 5, 7, 8, 9];
    themeLevel.forEach(function (level) {
      setPropertyColor(
        varName.replace("#level#", level),
        themeColor,
        fun,
        level
      );
    });
  }

  return {
    setThemeColor,
  };
});

