
object IncomeCRVReader extends App {
    //println("Month, Income, Expenses, Profit")
    val bufferedSource = io.Source.fromFile("../adult.data")
    var rich = 0
    var poor = 0
    var count = 0
    for (line <- bufferedSource.getLines) {
        val cols = line.split(",").map(_.trim)
        // do whatever you want with the columns here
        //println(s"${cols(0)}|${cols(8)}|${cols(9)}|${cols(14)}")

        //maybe i can use pattern matching here instead of if statements?
        if (cols(14) == ">50K") rich+=1 else poor+=1
        count+=1
    }
    println(s"greater than 50k: $rich")
    println(s"less or equal to 50k: $poor")
    println(s"There are this many datasets: $count")
    bufferedSource.close
}

//concurrency ideas - count each column at same time(?)