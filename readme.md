Reflection
What is working well, what you're struggling with (if anything), what you'd like to try differently on the next assignment?

Musa's Reflection:

What's working pretty well so far is definitely array indexing, or at least indexing through an arraylist. We're starting to understand more and more how to use indexing to achieve our goals. One thing that helped us do that was definitely drawing out the arraylist and manually working out the logic of the indexing whenever we encountered a bug. One bug that we encountered in indexing was while we were trying to truncate our offspring chromosome whenever the size of its chromosome turned out > 20. In this case, we learned that after each loop in the for loop, the size of the list would update, thus introducing a bug where our chromosome would not actually cut itself to a size that = 20. We were able to look at the documentation of different methods like subList to actually truncate our list instead as a subList of the original and make it = to the offspring's chromosome again. This was just one bug we had to figure out through the logic of indexing. 
    
While manually working out the logic of indexing and the rest of our programming was the right way to go, we definitely struggled with it sometimes. Using print statements to debug and drawing out diagrams definitely helped though. On the next assignment, I definitely want to work on using maybe less for loops and maybe more of the methods built into the classes we import to solve some problems. Our code works though, and I'm proud we made it this far! On to the next assignment, where I can start working on this new goal!

Eva's Reflection:
Something that was easy to understand was the order in which the methods should be used/called for the program to work. For example, we knew pretty early on that rankPopulation had to be working in order for evolve to work properly, since evolve takes the top-ranking population members that rankPopulation ranks. It was very tempting to call the preexisting methods within the new methods as we were making them, but we had to keep reminding ourselves that the methods should only be called in the run method. In assignments where creating tests isn't explicitly required, creating something similar to this assignment's run method to call all the methods in and find issues there is something I'd definitely like to do for future homework.




