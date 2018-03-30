#include <stdio.h>
#include <libxml/parser.h>
#include <libxml/tree.h>


int main(int argc, char **argv) {
  xmlDoc *doc = NULL;
  //xmlNode *root_element = NULL;
  if (argc != 2) return(1);
    /*parse the file and get the DOM */
    doc = xmlReadFile(argv[1], NULL, 0);
    if (doc == NULL){
       printf("Parse erreur ! (%s)\n", argv[1]); 
       return 1;
    }
  printf("Le fichier %s :\n", argv[1]);
  xmlDocDump(stdout, doc);
  printf("\n\n");
        /*free the document */
  xmlFreeDoc(doc);
         /*Free the global variables that may have been allocated by the parser. */
  xmlCleanupParser();
  return 0;
}
