[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/aLllu8fy)
# tJavaModule06spr25
tJavaModule06spr25

CIT-63 Midterm Programming Assignment: "Zookeeper's Challenge" – 100 Points

Assignment Insights:

You will develop a Java application titled "Zookeeper's Challenge," designed to simulate the management of a dynamic zoo environment. This assignment is an exceptional opportunity to demonstrate your proficiency in writing code that is not only syntactically sound but also logically coherent and in alignment with our class's coding conventions.

Your task involves processing two crucial documents: arrivingAnimals.txt and animalNames.txt. These files will serve as the foundation for your program, guiding the logic and functionality of your code.

Key Objectives:

Extract essential details (age, sex, species, color, weight) from arrivingAnimals.txt.
Calculate birthdays based on the provided data.
Utilize animalNames.txt to assign unique names to each animal.
Generate a unique ID for every animal, symbolizing their species and a sequential number.
Strategically organize animals into their respective habitats.
Compile a detailed report (zooPopulation.txt) showcasing the organized zoo population, complete with names, attributes, and habitat information.
Critical Methods to Implement:

genBirthDay(): Derive an animal's birthday from the data. Adapt your logic for cases where the birth season is not provided.  Use ISO 8601 format for dates. 
genUniqueID(): Formulate a unique identifier for each animal, ensuring no two animals share the same ID. Do not use an automatic ID generator that gives you a number between 1 and a trillion - Use "Hy01" for the first hyena, "Hy02" for the second hyena, "Li01" for the first lion, etc. If you've been coding up the previous examples of the static member (variable) named "numOfHyenas, numOfLions," etc., this will be easy for you.
In Java, every piece of functionality is encapsulated within classes, hence we refer to functions as methods. If the term "function" appears in any assignment context, it's synonymous with "method" in Java.

Your program will use file I/O operations to read from and write to files, employing data structures to organize the zoo data into information..

Format Specifications: You will be dealing with a variety of animal descriptions and tasked with structuring this information cohesively. An example input could look like:

"4 year old female hyena, born in spring, tan color, 70 pounds, from Friguia Park, Tunisia"
Expected Output: Your output should neatly categorize animals into their habitats, creating an organized snapshot of the zoo's population, like so:

Hyena Habitat:
Hy01; Kamari; birth date: 2018-03-21; tan color; female; 70 pounds; from Friguia Park, Tunisia; arrived 2024-03-26
Deliverables:

The Java program source files.
The zooPopulation.txt report.
A YouTube video that explains your program's architecture, the rationale behind your method implementations, and a live demonstration showcasing its execution. This narrative will enrich your portfolio, giving voice to your coding journey and insights.
Encouragement Note: This project is your canvas—paint it with the skills, creativity, and passion for coding you possess. Envision presenting your "Zookeeper's Challenge" project in professional or academic settings, showcasing your initiative, problem-solving capabilities, and attention to detail
