#include <stdio.h>
#include <libxml/parser.h>
#include <libxml/tree.h>

int main(int argc, char **argv){
  xmlDoc *doc = NULL;
  xmlNode *root_node = NULL, *node = NULL;
  if (argc != 2)return(1);
  doc = xmlReadFile(argv[1], NULL, 0);
  if(doc == NULL){
    printf("Parse erreur ! (%s)\n", argv[1]); return 1;
  }
  root_node = xmlDocGetRootElement(doc);
      /* Associate the root_node with document */
  xmlDocSetRootElement(doc, root_node);
   /* Add new sub node */
  node = xmlNewNode(NULL, BAD_CAST "livre");
  xmlNewChild(node, NULL, BAD_CAST "titre", BAD_CAST "Java");
    // ... etc ... : à compléter
  xmlNewChild(node, NULL, BAD_CAST "auteur", BAD_CAST "Rodrigo");
  xmlNewChild(node, NULL, BAD_CAST "codeisbn", BAD_CAST "2-8954-5264-2");
  xmlNewChild(node, NULL, BAD_CAST "editeur", BAD_CAST "CHASTAINC");

  xmlAddChild(root_node, node);
    /* Saving the file */
  xmlSaveFormatFileEnc(argv[1], doc, "UTF-8", 1);
  xmlFreeDoc(doc);
  xmlCleanupParser();
  return 0;
}
