# Algorhythm Widget Design Doc

## Who, What, When, Where, How, Why

### Who is the Algorhythm for?

Anyone who doesn't want to think too hard about the question, "What should I being right now?"

### What is the Algorhythm?

The Algorhythm is an algorithm for life! Think of a flowchart or conditional, iterative business logic for your day. Given an algorithm for the day, Algorhythm will simply present where you are in the sequence. When you are done (or if you choose to skip), you simply move on to the next action or decision point.

Algorhythms can be nested in one another in order to support modular design of one's time across different days.

### When is the Algorhythm meant to be used?

The Algorhythm is meant to be the first if not one of the first widgets referred to in the Millaggregator dashboard.

### Where is the Algorhythm

In the user experience, the Algorhythm is front and center. Codewise, it lives with the rest of the Millaggregator *(Or should it? Would it be better off as a microservice)*

### How does the Algorhythm work?

On the widget page, a user can CRUD their algorithms/flowcharts. These can refer to each other (barring circular references).

On the dashboard, the Algorhythm shows the current step of the day the user is at, along with an option to mark as completed (or skip) a particular step. On user action, the Algorhythm shows the next step of the day.

Some algorithms can simply be selecting a random action from a list (for example, do 20 pushups or 20 squats).

### Why the Algorhythm?

A day is much more nuanced than a list of to-dos. Habits are practiced, breaks are taken, difference to-do lists are relevant to different parts of the day. The Algorhythm is a once-stop-shop for What To Do and in what order, making for a more focused and friction-free day.

## Algorithm Examples

These examples are in the [flowchart.js DSL](http://flowchart.js.org/).

### Weekday

```
st=>start: Wake Up
opCat=>operation: Cat cleanup & feed
cond1=>condition: >1 hour before work?
op2=>operation: Get ready for work
sub1=>subroutine: Exercise
op3=>operation: Check Slack, email, Jira, to-dos
opSet=>operation: Set/review day's goals
subWork=>subroutine: Work
condZone=>condition: In the zone?
cond25=>condition: ~25 min. passed?
cond55=>condition: ~55 min. passed?
opRec=>operation: Record: 1. Progress 2. Learnings 3. Next Steps
opLimb=>operation: Limber body, limber mind.
condMeet=>condition: Meeting?
opMeet=>operation: 1. Open doc. 2. Attend meeting. 3. Record notes.
cond3h=>condition: 3 hours passed?
condLunch=>condition: Lunch?
condEnd=>condition: End of work day?
condEx=>condition: Exercised?
opCatPost=>operation: Cat clean & feed again
subFree=>subroutine: Free Time
condBed=>condition: <1 hour before bed?
opAmend=>operation: Amend to-dos
opJournal=>operation: Journal and/or meditate
e=>end: Rest

st->opCat->cond1
cond1(yes)->sub1
cond1(no)->op2
op2->op3->opSet->subWork->condZone
condZone(yes)->cond25
condZone(no)->cond55
cond25(yes)->opRec
cond25(no)->subWork
cond55(yes)->opRec
cond55(no)->subWork
opRec->opLimb->condMeet
condMeet(yes)->opMeet
condMeet(no)->cond3h
opMeet->subWork
cond3h(yes)->condLunch
cond3h(no)->subWork
condLunch(yes)->condEx
condLunch(no)->condEnd
condEx(yes)->opCatPost
condEx(no, left)->sub1
sub1->condEnd
condEnd(yes)->condEx
condEnd(no)->op2
opCatPost->subFree->condBed
condBed(yes)->opAmend
condBed(no)->subFree
opAmend->opJournal->e
```

While this syntax is limited to `flowchart.js` DSL, it will be further parsed by the client to provide dynamic algorithm steps and integration with other widgets. For example, a stepping into certain sub-routine may result in stepping through the entire sub-routine as defined in a separate algorithm, engaging in a single random step or sequential step based on history of a separate algorithm, or engaging in a single step generated from the state returned from an external widget API.

Weaving external elements into an algorithm will be done through the `subroutine` steps, with the name of a `subroutine` being critical to linking it to an external element. 

- If the name of a `subroutine` is identical to that of another algorithm, the subroutine consists of that algorithm.
- If the name of a `subroutine` follows the pattern `WIDGET_IDENTIFIER: FUNCTION_IDENTIFIER`, then the step will be injected with the result of that widget function. 
