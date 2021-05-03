function compile_all {
    compile_constant
    compile_board
    compile_piece
    compile_reversi
    compile_DoNothing
    compile_EatLongEnemy
}

function compile_constant {
    echo "Compling Constant"
    javac ./main/Constant.java &&  echo "Compling Board Completed."
}

function compile_board {
    echo "Compling Board..."
    javac ./main/Board.java && echo "Compling Board Completed."
    
}

function compile_piece {
    echo "Compling Piece..."
    javac ./main/Piece.java && echo "Compling Piece Completed."
    
}

function compile_reversi {
    echo "Compling Reversi..."
    javac ./main/Reversi.java && echo "Compling Reversi Completed."
    
}

function compile_DoNothing {
    echo "Compling DoNothing..."
    javac ./action/DoNothing.java && echo "Compling DoNothing Completed."
    
}

function compile_EatLongEnemy {
    echo "Compling EatLongEnemy..."
    javac ./action/EatLongEnemy.java && echo "Compling EatLongEnemy Completed."
    
}

 if [ "$1" == "all" ]; then
        compile_all
    elif [ "$1" == "board" ]; then
        compile_board
    elif [ "$1" == "reversi" ]; then
        compile_reversi
fi
echo "=== Complied! ==="
java main.Reversi
#command => "sh compile.sh all"