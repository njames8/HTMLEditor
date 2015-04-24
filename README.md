# HTMLEditor
Java based application to edit HTML
Instructions:

  Outline View:

    1. Turn on by "View" -> "Outline View"

    2. Collapse tags by "Format" -> "Collapse"


  Image Preview:

    1. Insert Image

    2. Ctrl + Left Click to view Preview


  Link View:

    1. Turn on by "View" -> "Link View"


Limitations of Implementation:

  1. Undo does not fully remove a tag. It removes the text but not the node in the tree.


  2. Undo and redo produce exceptions when the stack is empty. These exceptions do not effect the functionality of the editor


  3. Undo does not undo collapsed tags


  4. Link View does not get rid of duplicates or show occurrences


  5. Outline view does weird things when collapsing tags.


  6. On some machines, the default window size is not big enough for all of the items on the LinkView Menu


  
