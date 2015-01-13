This Eclipse plugin adds the FreeMarker documentation to the Eclipse help
system, so you can use the full-text searching capability of the Eclipse
help system to search the FreeMarker documentation.

Installation:

1. Copy the "docs" directory of the FreeMarker distribution into
   this directory (into the org.visigoths.freemarker.help directory).
   
   You need at least FreeMarker 2.3rc1, or 2.2.6 from the 2.2.x series!
   
2. Move this directory (org.visigoths.freemarker.help) into
   the "plugins" directory of you Eclipse installation.
   
3. Restart Eclipse if it is running. Now you will find the "FreeMarker"
   entry in the Eclipse help (Help/Help Contents).
   
Updating for a new FreeMarker release:

1. Simply replace the
   <yourEclipseInstallation>/plugnins/org.visigoths.freemarker.help/docs
   directory with the "docs" directory comes with the new FreeMarker
   distribution.
   
2. You may need to restart Eclipse if it is running.
