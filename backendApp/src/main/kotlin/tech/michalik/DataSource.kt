package tech.michalik

fun nextRandomJoke() = jokes.random()

val jokes = """
    Why didn't the skeleton cross the road?
  Because he didn't have any guts
 
  Why did the mummy take the stress reduction class?
  He was all wound up

  What do goblins mail home while on vacation?
  Ghost-Cards!
  
  Where did they put Dracula when he was arrested?
  Into a blood cell

  Why do vampires always use mouthwash after every meal?
  So they won't get bat breath

  How do you know a vampire is getting a cold?
  When he starts coffin

  Why doesn't Dracula have any friends?
  Because he's a pain in the neck

  Why is there always a fence around a cemetery?
  Because people are just dying to get in

  Why do vampires drink blood? 
  Because coffee keeps them up all day

  Why does a ghost have trouble lying?
  You can see right through him

  Do monsters like to eat popcorn with their fingers?
  No, they prefer to eat fingers separately

  How do you keep a skeleton from laughing?
  Take away his funny bone

  Where do little ghosts learn to yell "BOO!"?
  In noisery school.
  
  What does a goblin shop for?
  Grosseries.
  
  What do you call serious rocks?
  Grave stones

  How does a witch travel when she doesn't have a broom?
  She witch hikes

  How many witches does it take to change a light bulb?
  Only one, but she changes it into a frog

  What kind of horse does a ghost ride?
  Night mare

  Why do witches fly on brooms?
  Because vacuum cleaners are too expensive

  How did the monster predict his future?
  With a horror-scope.
  
  What is a vampire's favorite breed of dog?
  Bloodhound

  Why do vampires tend to make great artists?
  They got lots of practice drawing blood

  What is a vampires favorite holiday?
  Fangsgiving.
  
  What spook lives in the "hundred acre wood"?
  Winnie the Boo
  
  What do spooks call their Navy?
  The ghost guard
  
  Where do vampires go to deposit their savings?
  The blood bank

  Why was the boy unhappy to win 1st prize for the best costume at the Halloween party?
  Because he had just come to pick up his sister

  Why couldn't the skeleton go to the dance?
  Because he had no body to take
  
  What flies through the night, has a black cape, and bites people?
  A mosquito wearing a black cape
  
  In what room of the house would you never find a ghost?
  The living room.
   
  How much does a truck full of bones weigh?
  A skel-e-ton
  
  How do little ghosts get to school? 
  On the ghoulbus
    """.trimIndent().split("\n\n")