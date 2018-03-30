#include <stdio.h>
#include <stdlib.h>
#include <libxml/tree.h>
#include <libxml/xpath.h>
#include <libxml/xmlreader.h>

int main (int argc, char *argv[])
{
   xmlDocPtr xmldoc = NULL;
   const char doc[] ="<?xml version='1.0'?><racine><texte>blabla</texte></racine>";
   xmldoc = xmlParseMemory (doc, sizeof (doc));
   if (!xmldoc)
     {
       fprintf (stderr, "%s:%d bah ca marche pas du tout, en fait \n", __FILE__,__LINE__);
      exit (EXIT_FAILURE);
   }
   printf ("%s\n", xmlNodeGetContent(xmldoc->children->children));
   xmlFreeDoc (xmldoc);
   exit (EXIT_SUCCESS);
 }
