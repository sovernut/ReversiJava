function compile_all {
    compile_constant
    compile_board
    compile_piece
    compile_reversi
}

function compile_constant {
    echo "Compling Constant"
    javac Constant.java
    echo "Compling Board Completed."
}

function compile_board {
    echo "Compling Board..."
    javac Board.java
    echo "Compling Board Completed."
}

function compile_piece {
    echo "Compling Piece..."
    javac Piece.java
    echo "Compling Piece Completed."
}

function compile_reversi {
    echo "Compling Reversi..."
    javac Reversi.java
    echo "Compling Reversi Completed."
}

 if [ "$1" == "all" ]; then
        compile_all
    elif [ "$1" == "board" ]; then
        compile_board
fi
echo "=== Complied! ==="
java Reversi
#command => "sh compile.sh all"