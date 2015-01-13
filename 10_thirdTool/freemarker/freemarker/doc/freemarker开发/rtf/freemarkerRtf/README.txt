RTF-generation helper classes & templates for Freemarker.
Author: Assaf Urieli, Joliciel Informatique, http://www.joli-ciel.com, assaf_at_joli-ciel_dot_com
Date: 30-Sep-2005
Version: 0.4
All source code contributed to the Public Domain.

***** Summary *****
Produces fairly complex RTF (including unicode support for certain languages) without any actual RTF directives in the template,
by using an RTF macro library.
Unicode support is provided by the RTFConverter class. This class also takes care of RTF-escaping your interpolations (much like
the ?rtf built-in) as well as maintaining interpolation newlines in the RTF produced.
The class is most easily used by wrapping the whole template by an escape:
<#escape x as RtfConverter(x)>...</#escape>

***** Notes *****
Source files are UTF-8!
The RTF produced has only been tested with Word 2002 SP2 and WordPad 5.1 on Windows XP.

***** Fonts *****
Includes support for ASCII, West/Eastern/Central European, Cyrillic, Greek, Hebrew (including Yiddish!) and simple Arabic alphabets.
Also includes support for right-to-left and left-to-right.
Other alphabets could be added easily enough, but I ran out of time.
Current font support is very limited - you need to choose one main font per language (by modifying rtf.ftl if you don't like my choices).
The default size (configurable in rtf.ftl) is currently set to 10 points. The H1, H2, big, small and fontSize macros give you other sizes.
RTF being the mess that it is, the idea was to keep it as simple as possible.

***** Tables *****
As of v0.3, you may specify column spans for table cells - see the row macro for details.
Bullets now work inside table cells as well.

Feel free to contact me at the above e-mail address if you want to report bugs or get more explanations/enhancements.

Dependencies:
freemarker.jar (duh) - tested with 2.3.3