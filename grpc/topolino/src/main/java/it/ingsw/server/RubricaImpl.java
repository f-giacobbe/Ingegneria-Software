package it.ingsw.server;

import io.grpc.stub.StreamObserver;
import it.ingsw.proto.*;

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
    }

    @Override
    public void getContact(NomeContatto request, StreamObserver<Person> responseObserver) {
        Person res = rubrica.get(request);

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(ListRequest request, StreamObserver<ContactList> responseObserver) {
        ContactList res = ContactList.newBuilder().addAllContatti(rubrica.values()).build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
