# UML Diagram:

## How to understand UML Diagram ?
### Access modifiers symbols meanings :

+: Public
    This class member can be accessed from anywhere.
-: Private
    This class member can only be accessed within the same class.
#: Protected
    This class member can be accessed within the same class and outside only in a child class.

### Class relations lines meanings :
→: Inheritance
    A relationship in which a class extends another class.
⇢: Dependancies
    A relationship in which one element benefits from or depends on another element.
◇: Aggregation
    Relationship in which an element uses another element.
◆: Composition
    Relationship in which the life cycles of the elements and the aggregate are linked: if the aggregate is destroyed, so are its components.

## How to compile use ?

### Compile the project
```make```
### Delete class files only
```make clean```
### Delete class files and binary
```make fclean```
### Rebuild the project
```make re```