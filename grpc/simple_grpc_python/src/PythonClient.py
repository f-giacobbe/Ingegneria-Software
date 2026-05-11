import grpc
import Greeter_pb2 as Action
import Greeter_pb2_grpc as Service
import calculator_pb2_grpc as CalculatorService
import calculator_pb2 as CalculatorAction
import rubrica_pb2 as RubricaAction
import rubrica_pb2_grpc as RubricaService


def greeter(channel):
    name = input("Inserisci il tuo nome: ")
    print("Invio il tuo nome al server gRPC\n")

    stub = Service.GreeterStub(channel)
    response = stub.SayHello(Action.HelloRequest(name=name))

    print("Il server gRPC dice: " + response.message)

def calculator(channel):
    opA = 5
    opB = 6

    stub = CalculatorService.CalculatorStub(channel)
    response = stub.Add(CalculatorAction.OperationRequest(opA=opA, opB=opB))

    print("Il server gRPC dice: " + str(response.res))

def rubrica(channel):
    stub = RubricaService.RubricaStub(channel)

    c1 = RubricaAction.Person(nome="Francesco", cognome="Giacobbe", telefono=123)
    c2 = RubricaAction.Person(nome="Topolino", cognome="Pippo", telefono=456)

    stub.AddContact(c1)
    print( stub.GetAll( RubricaAction.ListRequest() ).contatti)


def run():
    with grpc.insecure_channel('localhost:50051') as channel:
        # greeter(channel)

        # calculator(channel)

        rubrica(channel)


if __name__ == '__main__':
    run()
