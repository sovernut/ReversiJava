function compile_all {
    compile_board
    compile_piece
    compile_reversi
}

function compile_board {
    echo "Compliing Board..."
    javac Board.java
    echo "Compliing Board Completed."
}

function compile_piece {
    echo "Compliing Piece..."
    javac Piece.java
    echo "Compliing Piece Completed."
}

function compile_reversi {
    echo "Compliing Reversi..."
    javac Reversi.java
    echo "Compliing Reversi Completed."
}

 if [ "$1" == "all" ]; then
        compile_all
    elif [ "$1" == "board" ]; then
        compile_board
fi
echo "=== Complied! ==="
java Reversi
