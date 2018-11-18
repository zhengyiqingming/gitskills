package com.baizhi.ftl;

public class ${className} {

<#--
    key value
    id  integer
    name string
-->
<#list map?keys as key>
        private ${map[key]} ${key};
</#list>

<#list map?keys as key>
        public ${map[key]} ....{

        }
</#list>
}
