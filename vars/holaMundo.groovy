def call(String name = 'User') {
    echo "Goodbye, ${name}."
    slackSend channel: 'Proyecto Fenix', message: "Goodbye, ${name}."
}

def informacion(String name = 'User') {
    echo "Informacion de la libreria de ${name}"
}
