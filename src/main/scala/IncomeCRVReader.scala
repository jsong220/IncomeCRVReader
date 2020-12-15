package IncomeCRVReader

import org.mongodb.scala_

object IncomeCRVReader extends App {
    val bufferedSource = io.Source.fromFile("adult.data")
    var rich = 0
    var poor = 0
    var count = 0
    var richMale = 0
    var richFemale = 0
    var poorMale = 0
    var poorFemale = 0

    val mongoClient = MongoClient("mongodb://mongo")
    val database = mongoClient.getDatabase("Proj0DB")
    val collection = database.createCollection("exampleCollectionName")


    for (line <- bufferedSource.getLines) {
        val cols = line.split(",").map(_.trim)
        // do whatever you want with the columns here
        //println(s"${cols(0)}|${cols(8)}|${cols(9)}|${cols(14)}")

        //maybe i can use pattern matching here instead of if statements?
        if (cols(14) == ">50K") rich+=1 else poor+=1
        count+=1

        //mix gender and income
        //error-none of these are being match..maybe try pattern matching?
        if(cols(9) == "Male" && cols(14) == ">50k"){
            richMale+=1
        }else if (cols(9) == "Female" && cols(14) == ">50k"){
            richFemale +=1
        }else if (cols(9) == "Male" && cols(14) == "<=50k"){
            poorMale+=1
        }else if (cols(9) == "Male" && cols(14) == "<=50k"){
            poorFemale+=1
        }else{
            //println("not matching anything")
        }
    }
    println(s"greater than 50k: $rich")
    println(s"less or equal to 50k: $poor")
    println(s"There are this many datasets: $count")
    println(s"There are this many rich males: $richMale")
    println(s"There are this many rich females: $richFemale")
    println(s"There are this many poor males: $poorMale")
    println(s"There are this many poor females: $poorFemale")
    bufferedSource.close
    
    // for (i <- 0 to 5) println(i)
    // for (i <- 0 to 10 by 2) println(i)
    println(("MyNameIsJohn").toList)

    println(database.name)

}



//concurrency ideas - count each column at same time(?)
//out in crv format..or diff formats.
//options for diff types of anyalsis                                         

