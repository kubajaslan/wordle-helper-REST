This REST api provides a list of possible answers for the Wordle game by taking into account the letters that should be excluded or included at specific indices.

Link to the wordle game:

https://www.nytimes.com/games/wordle/index.html


The app is live at:

https://wordle-rest-api.lm.r.appspot.com/words



HOW TO USE THIS API?

For simplicity we’re going to assume the server is running on localhost:8080.

 — To view a complete list of all the possible answers go to:


http://localhost:8080/words

—  To restrict the list to words which DO NOT contain given letters: 

http://localhost:8080/words/param?exclude={abc}

*{abc} can be replaced by any sequence of letters.

—  To restrict the list to words which CONTAIN given letters but not at a specific index (the yellow letters in the game: 

http://localhost:8080/words/param?yellow={1a2b3c}

*{1a2b3c} can be replaced by any sequence of digits and letters, keeping in mind that the digit signifies the index in the word of the letter highlighted yellow in the game.


—  To restrict the list to words which CONTAIN given letters AT a specific index (the greenletters in the game: 

http://localhost:8080/words/param?green={1a2b3c}

*{1a2b3c} can be replaced by any sequence of digits and letters, keeping in mind that the digit signifies the index in the word of the letter highlighted green in the game.


All these parameters can be used at the same time with the “&” character inbetween them.

For example, the call:

http://localhost:8080/words/param?exclude=abc&yellow=1s&green=5y1m

Results in the API returning the list:


[
"musty",
"musky",
"missy",
"mushy",
"mossy"
]


Let us analyse this: the exclude=abc parameter means that none of these words should contain the letter A, B, or C. Indeed, none of them does.

Secondly, the parameter “yellow=1s” means that while the word should contain the letter S somewhere, it should not be found at index 1.

Lastly, the parameter “green=5y1m” means that the word should contain the letter Y at index 5 and it should contain the letter M at index 1.

If you wish to reset the list of words go to:

http://localhost:8080/words/reset


Here is an example of an app which could use such an API:

https://www.youtube.com/watch?v=iopa70XnrDY&t=11s



