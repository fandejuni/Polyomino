class Main {
    public static void main(String[] args) {
        Polyomino P = new Polyomino("[(0,0), (0,1), (0,2), (0,3), (0,4), (1,2), (1,3), (2,1), (2,2), (3,0), (3,1), (3,2), (3,3), (3,4)]");
        System.out.println(P);
        // P.draw();
        Polyomino.draw(Polyomino.openFile());
    }
}
