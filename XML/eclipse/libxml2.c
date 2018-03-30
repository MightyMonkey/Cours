#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libxml/tree.h>
#include <libxml/xpath.h>

void show (xmlNodePtr node, xmlChar* path)
{
 if (node->type == XML_ELEMENT_NODE){
  xmlNodePtr n;
  for (n = node; n; n = n->next){
    if (n->children) {
     xmlChar *path = xmlGetNodePath(node);
     show (n->children, path);
    }

  }
 } else if ((node->type == XML_CDATA_SECTION_NODE) || (node->type == XML_TEXT_NODE)) {
    //xmlChar *path = xmlGetNodePath(node);
    printf ("%s -> '%s'\n", path,node->content ? (char *) node->content : "(null)");
    xmlFree (path);
 }
} // end show

int
main (int argc, char *argv[]){
  xmlDocPtr xmldoc = NULL;
  const char doc[] ="<?xml version='1.0'?><racine><texte>blabla</texte><t2>ca</t2><t3>va</t3><t4><![CDATA[<bien?>]]></t4></racine>";
  xmldoc = xmlParseMemory (doc, sizeof (doc));
  if (!xmldoc){
    fprintf (stderr, "%s:%d bah ca marche pas du tout, en fait\n", __FILE__,__LINE__);
    exit (EXIT_FAILURE);
  }
  xmlChar *path = xmlGetNodePath(xmldoc->children);
  show (xmldoc->children, path);
  xmlFreeDoc (xmldoc);
  exit (EXIT_SUCCESS);
} //end main

