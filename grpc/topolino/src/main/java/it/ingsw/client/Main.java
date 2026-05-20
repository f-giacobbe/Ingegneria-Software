package it.ingsw.client;

import com.google.cloud.location.ListLocationsRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import it.ingsw.proto.*;

public class Main {
    private static void calculator(ManagedChannel channel) {
        CalculatorGrpc.CalculatorBlockingStub stub =        // Blocking stub -> sincrono
                CalculatorGrpc.newBlockingStub(channel);

        OperationRequest request = OperationRequest.newBuilder()
                .setOpA(1)
                .setOpB(2)
                .build();
        OperationResponse or = stub.add(request);
        System.out.println("Risultato addizione:" + or.getRes());
    }

<<<<<<< HEAD
    private static void rubrica(ManagedChannel channel) {
        RubricaGrpc.RubricaBlockingStub stub = RubricaGrpc.newBlockingStub(channel);

        Person p1 = Person.newBuilder().setNome("Francesco").setCognome("Giacobbe").setTelefono(123).build();
        stub.addContact(p1);

        ContactList contatti = stub.getAll(ListRequest.newBuilder().build());
        contatti.getContattiList().forEach(System.out::println);
        System.out.println("------------------------------");

        Person p2 = Person.newBuilder().setNome("Topolino").setCognome("Topolinis").setTelefono(456).build();
        stub.addContact(p2);
        contatti = stub.getAll(ListRequest.newBuilder().build());
        contatti.getContattiList().forEach(System.out::println);
        System.out.println("------------------------------");

        NomeContatto query = NomeContatto.newBuilder().setNome("Francesco").setCognome("Giacobbe").build();
        Person queried = stub.getContact(query);
        System.out.println(queried);
    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50010)
                .usePlaintext()
                .build();

        rubrica(channel);
=======
        RubricaGrpc.RubricaBlockingStub rubricaStub = RubricaGrpc.newBlockingStub(channel);


        ContactList lr = rubricaStub.getAll(null);
        System.out.println("LA MIA RUBRICA:" + lr);

>>>>>>> main

        channel.shutdown();
    }
}