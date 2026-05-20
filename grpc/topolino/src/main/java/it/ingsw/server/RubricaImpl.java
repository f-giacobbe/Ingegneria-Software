package it.ingsw.server;

import io.grpc.stub.StreamObserver;
import it.ingsw.proto.*;
<<<<<<< HEAD

import java.util.concurrent.ConcurrentHashMap;

public class RubricaImpl extends RubricaGrpc.RubricaImplBase {
    private final ConcurrentHashMap<NomeContatto, Person> rubrica = new ConcurrentHashMap<>();

    @Override
    public void addContact(Person request, StreamObserver<Result> responseObserver) {
        String nome = request.getNome();
        String cognome = request.getCognome();
        int telefono = request.getTelefono();
        NomeContatto nc = NomeContatto.newBuilder().setNome(nome).setCognome(cognome).build();
        Person p = Person.newBuilder().setNome(nome).setCognome(cognome).setTelefono(telefono).build();

        Person ins = rubrica.put(nc, p);

        Result res = Result.newBuilder().setRes(ins != null).build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
=======
import java.util.concurrent.ConcurrentHashMap;

public class RubricaImpl extends RubricaGrpc.RubricaImplBase {

    private ConcurrentHashMap<String, Person> contacts = new ConcurrentHashMap<>();

    @Override
    public void addContact(Person request, StreamObserver<Result> responseObserver) {

        Person person = contacts.put(request.getNome(), request);
        if (person == null){
            responseObserver.onNext(null);
            responseObserver.onCompleted();
            return;
        }
        Result res = Result.newBuilder().setCreato(true).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();

>>>>>>> main
    }

    @Override
    public void getContact(NomeContatto request, StreamObserver<Person> responseObserver) {
<<<<<<< HEAD
        Person res = rubrica.get(request);

=======
        String nome = request.getNomeContatto();

        /*
        contacts.keys().asIterator().forEachRemaining(nomeContatto ->{
            if(nomeContatto.contains(nome)){
                Person p = contacts.get(nomeContatto);
                responseObserver.onNext(p);
                responseObserver.onCompleted();
            }
        });*/

        Person res = contacts.get(nome);
        if(res == null)
            res = Person.newBuilder().build();
>>>>>>> main
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(ListRequest request, StreamObserver<ContactList> responseObserver) {
<<<<<<< HEAD
        ContactList res = ContactList.newBuilder().addAllContatti(rubrica.values()).build();

=======
        ContactList res = ContactList.newBuilder()
                .addAllPerson(contacts.values())
                .build();
>>>>>>> main
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
