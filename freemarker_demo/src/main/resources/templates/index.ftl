<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    <#include "head.ftl">
<#-- FTL语法 取值表达式 取name value -->
Hello: ${name}
<hr>
<!-- html注释-->
<#-- 取值: 获取对象的信息 key.username key.id-->
${user.id} | ${user.username}
<hr>
<#-- 取值: 获取数字类型的value-->
${age}<br>
${age?string}<br>
${age?string.currency}<br>
${age?string.percent}<br>
<hr>

<#-- 取值: 获取日期类型的value   datetime date time-->
${birthday?datetime}<br>
${birthday?date}<br>
${birthday?time}<br>

${birthday?string("yyyy年MM月dd日 HH:mm:ss")}

<hr>
<#-- 取值: 获取boolean类型的value-->
${bool?c}<br>
${bool?string("yes","no")}

<hr>
<#-- 标签: #list 遍历集合 数组 list set map-->
    <#list list as l>
        <li>${l}</li>
    </#list>

<hr>
<#-- 标签: #list 遍历map集合 键遍历-->
    <#list map?keys as key>
        <li>${key} | ${map[key]}</li>
    </#list>

<hr>
<#-- 标签: #if 条件判断-->
    <#if bool>
        今天天气很好
    <#elseif name=="zs">
            立冬了,该穿秋裤了
    <#else>
        今天天气很冷
    </#if>

<hr>
<#-- 空值处理 要求严格-->
${exists!"默认值"}

    <#if exists??>
        值存在
    <#else>
        值不存在
    </#if>

<hr>
<#-- macro 自定义标签-->
<#-- 声明自定义标签-->
    <#macro test>
        自定义标签
    </#macro>

<li>
        <@test />
</li>
<li>
        <@test />
</li>

<#-- 声明了 有参数的自定义标签-->
    <#macro test1 param1 param2>
        ${param1} | ${param2}
    </#macro>

    <@test1 param1="aa" param2=5*5-2></@test1>
</body>
</html>