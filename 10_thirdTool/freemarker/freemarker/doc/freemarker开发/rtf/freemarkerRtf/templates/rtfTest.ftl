<#--
Written by Assaf Urieli, Joliciel Informatique (http://www.joli-ciel.com), assaf_at_joli-ciel_dot_com
Contributed to the Public Domain.
If you indent your templates for readability, make sure you trim them using the <#t> directive,
since RTF is white-space sensitive (except for newlines).
-->
<#import "rtf.ftl" as rtf>
<#escape x as RtfConverter(x)>
<@rtf.document>
	${greeting}<@rtf.newline/><#t>
	hello ${name}<@rtf.newline/><#t>
	How are you? ${reply}<@rtf.newline/><#t>
	A date: ${testDate?date?string.medium}<@rtf.newline/><#t>
	Rtf escapes: ${rtfEscapes}<@rtf.newline/><#t>
	<@rtf.bullet>some <@rtf.bold>bold</@rtf.bold> text...</@rtf.bullet><#t>
	<@rtf.bullet>some <@rtf.italic>italic</@rtf.italic> text...</@rtf.bullet><#t>
	some <@rtf.underline>underlined</@rtf.underline> text...<@rtf.newline/><#t>
	<@rtf.H1>My big header</@rtf.H1><#t>
	some <@rtf.big>big</@rtf.big> text...<@rtf.newline/><#t>
	some <@rtf.small>small</@rtf.small> text...<@rtf.newline/><#t>
	a mixture: <@rtf.bold>bold, <@rtf.italic>italic <@rtf.small>small <@rtf.big>big </@rtf.big>back to small</@rtf.small> regular-size</@rtf.italic> bold </@rtf.bold> regular text.<@rtf.newline/><#t>
	finally, a table:<#t>
	<#assign columns=4><#t>
	<@rtf.table columns><#t>
		<#list 1..3 as rowNum><#t>
			<@rtf.row><#t>
				<#list 1..columns as columnNum><#t>
					<@rtf.cell>row #{rowNum}, column #{columnNum}</@rtf.cell><#t>
				</#list><#t>
			</@rtf.row><#t>
		</#list><#t>
		<@rtf.row [1,2,1]>
			<@rtf.cell>left cell</@rtf.cell><#t>
			<@rtf.cell>middle cell</@rtf.cell><#t>
			<@rtf.cell>right cell</@rtf.cell><#t>	
		</@rtf.row>
		<@rtf.row [3,1]>
			<@rtf.cell>big left cell</@rtf.cell><#t>
			<@rtf.cell>little right cell</@rtf.cell><#t>
		</@rtf.row>
		<@rtf.row [1,3]>
			<@rtf.cell>little left cell</@rtf.cell><#t>
			<@rtf.cell><#t>
				big right cell with bullets:<@rtf.newline/><#t>
				<@rtf.bullet>bullet 1</@rtf.bullet><#t>
				<@rtf.bullet false>bullet 2</@rtf.bullet><#t>
			</@rtf.cell><#t>
		</@rtf.row>
	</@rtf.table><#t>
	<@rtf.underline>Unicode text before and after RTF bracketed unit: ${russian}</@rtf.underline>${russian}.<@rtf.newline/><#t>
	Okay, <@rtf.fontSize 13>that's all folks.</@rtf.fontSize><#t>
</@rtf.document>
</#escape>