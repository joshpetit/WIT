# WIT

WIT (When I Type) is the prototype for a software/hardware technology that aims to make it easier for
the visually impaired to interact with computers.

## Concept

The WIT concept is to use input patterns instead of the single inputs all keyboards currently use. 
One implementation goal is to make the order of keys pressed on a keyboard dictate what
action to perform. 

### Example

For Example, if the numbers 0-9 were to be assigned to certain keys on the keyboard, the order in which
those ten keys are pressed will dictate what is outputted.

```
0 + 5 -> A
0 + 6 -> B
0 + 7 -> C
5 + 0 -> Backspace
6 + 0 -> Shift
etc...
```

All of these combinations would be specified in a pre-built modifiable configuration file.

The benefit in this would be that the person using the keyboard would not need to memorize the placement
of specific lettered keys, just the orders to press the keys they select to use.

## Implementation

WIT is implemented in Java and aims to offer constructs that make it easy to implement different
uses. For instance, in the previous example, the interpreter that handles the inputs may "feed" into a different implementation that may issue different commands (i.e `0 + 5` may issue an `Open Tab` command
in a browser).

All of the packages will be exported so that implementations can be made by end-users, but WIT will offer its own created systems.

### WIT-CORE

WIT-CORE is the actual system that contains the classes to process inputs and output commands. The basic
concept is finished but actual implementations are still being created.

### WIT-GUI

WIT-GUI is planned to be a demo application that uses WIT-CORE and a possible configuration editor. It's currently implemented with a disabled textfield that implements
the needed classes, the goal is to create an implementation for all the textfield methods based upon the new typing system.

## Looking Forward

Creating a physical device is something that is very much in the plans and probably the largest future goal.

Plans:
- Implement an interpreter for a simple text application
- Create WIT-GUI for configuration editing/testing.
- Create a physical keyboard and its corresponding interpreter.
- Create systems for web browsers and popular applications

