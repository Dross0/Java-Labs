public class Main {
    public static void main(String[] args){
        if (args.length != 2){
            System.exit(1);
        }
        WordCounter wc = new WordCounter();
        wc.count(args[0]);
        wc.outputResult(args[1]);
    }
}
