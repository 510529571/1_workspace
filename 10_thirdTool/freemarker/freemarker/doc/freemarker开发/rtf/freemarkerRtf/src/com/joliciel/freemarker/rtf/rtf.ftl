<#--
Written by Assaf Urieli, Joliciel Informatique (http://www.joli-ciel.com), assaf_at_joli-ciel_dot_com
Contributed to the Public Domain.
If you indent your templates for readability, make sure you trim them using the <#t> directive,
since RTF is white-space sensitive (except for newlines).
-->
<#-- You may override any of these in your document -->
<#assign RTF_DefaultFontSize=10>
<#assign RTF_BigFontSize=12>
<#assign RTF_SmallFontSize=8>
<#assign RTF_H1FontSize=18>
<#assign RTF_H2FontSize=14>

<#assign RTF_InTable=false>
<#assign RTF_TableColumns=1>
<#macro document>
	{\rtf1\ansi\ansicpg1252\deff0\deflang1033<#t>
		{\fonttbl<#t>
			<#-- feel free to change these fonts (except for f100)... just make sure you keep the same charset if you want RtfStringModel to keep working! -->
			{\f0\fswiss\fcharset0 Arial;}<#t>
			{\f1\fswiss\fcharset238\fprq2 Arial CE;}<#t>
			{\f2\fswiss\fcharset204\fprq2 Arial Cyr;}<#t>
			{\f3\fswiss\fcharset161\fprq2 Arial Greek;}<#t>
			{\f4\fbidi \froman\fcharset177\fprq2 Times New Roman (Hebrew);}<#t>
			{\f5\fbidi \froman\fcharset178\fprq2 Times New Roman (Arabic);}<#t>
			{\f6\fswiss\fcharset186\fprq2 Arial Baltic;}<#t>
			{\f100\fnil\fcharset2 Symbol;}<#t>
		}<#t>
		\viewkind4\uc1\pard\f0\fs#{RTF_DefaultFontSize*2} <#t>
		<#nested><#t>
	}<#t>
</#macro>

<#macro newline>
	\par\f0\fs#{RTF_DefaultFontSize*2} <#t>
</#macro>

<#macro bold>
	{\b <#nested>\b0}<#t>
</#macro>

<#macro italic>
	{\i <#nested>\i0}<#t>
</#macro>

<#macro underline>
	{\ul <#nested> \ulnone}<#t>
</#macro>

<#macro small>
	{\fs#{RTF_SmallFontSize*2} <#nested>}<#t>
</#macro>

<#macro big>
	{\fs#{RTF_BigFontSize*2} <#nested>}<#t>
</#macro>

<#macro H1>
	{\fs#{RTF_H1FontSize*2}  <#nested>}<@newline/><#t>
</#macro>

<#macro H2>
	{\fs#{RTF_H2FontSize*2}  <#nested>}<@newline/><#t>
</#macro>

<#macro fontSize size>
	{\fs#{size*2} <#nested>}<#t>
</#macro>

<#macro bullet withNewline=true>
	<#-- you may wish to leave off the newline on the last bullet in a table cell, by passing in false above -->
	<#if RTF_InTable>
		{\pard\f100\'B7   }<#nested><#if withNewline>\par</#if><#t>
	<#else>
		{\pard{\pntext\f100\'B7\tab}{\*\pn\pnlvlblt\pnf100\pnindent0{\pntxtb\'B7}}\ltrpar\fi-300\li300\f0\fs#{RTF_DefaultFontSize*2} <#nested><#if withNewline>\par</#if>}<#t>
	</#if>
</#macro>

<#macro table columns>
	<#assign RTF_InTable=true>
	<#assign RTF_TableColumns=columns>
	{\par<#t>
	<#nested><#t>
	\pard\ltrpar\nowidctlpar}<#t>
	<#assign RTF_TableColumns=1>
	<#assign RTF_InTable=false>
</#macro>

<#macro row cellSpans=[]>
	<#-- note: for each column specified in table above, you will need to nest a "cell" element inside the "row" element -->
	<#-- cellSpans defines the column span of each cell, e.g. <@rtf.row [1,2,1]> gives x xx x for a 4 column table -->
	<#if RTF_InTable>
		\trowd\trgaph108\trleft-108\trbrdrl\brdrs\brdrw10 \trbrdrt\brdrs\brdrw10 \trbrdrr\brdrs\brdrw10 \trbrdrb\brdrs\brdrw10 \trpaddl108\trpaddr108\trpaddfl3\trpaddfr3<#t>
		<#if cellSpans?size==0>
			<#list 1..RTF_TableColumns as i><#t>
				\clbrdrl\brdrw10\brdrs\clbrdrt\brdrw10\brdrs\clbrdrr\brdrw10\brdrs\clbrdrb\brdrw10\brdrs \cellx#{(8748/RTF_TableColumns)*i; m0M0}<#t>
			</#list><#t>
		<#else>
			<#local columnSlice=(8748/RTF_TableColumns)>
			<#local currentColumnPos=0>
			<#list 1..cellSpans?size as i><#t>
				<#local currentColumnPos=currentColumnPos+(cellSpans[i-1]*columnSlice)>
				\clbrdrl\brdrw10\brdrs\clbrdrt\brdrw10\brdrs\clbrdrr\brdrw10\brdrs\clbrdrb\brdrw10\brdrs \cellx#{currentColumnPos; m0M0}<#t>
			</#list><#t>
		</#if>
		\pard\intbl\ltrpar\nowidctlpar <#nested>\row <#t>
	</#if>
</#macro>

<#macro cell>
	<#if RTF_InTable>
		\f0\fs#{RTF_DefaultFontSize*2} <#nested>\cell <#t>
	</#if>
</#macro>