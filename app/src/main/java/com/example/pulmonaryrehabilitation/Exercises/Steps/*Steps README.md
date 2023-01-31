Exercise class will use lists of type ExerciseStep. 

This is done so we can create different types of steps and put in the same list. 
(Yes Kotlin lists can have different types but that seems like bad practise)

When you need a step you will create an instance of TapStep, TimerStep, or whatever future types 
are created. This is done in the file 'Steps'.

Steps is a Singleton Object. You get steps by using Steps.stepName

StepActions enum is to tell the our player what to do.