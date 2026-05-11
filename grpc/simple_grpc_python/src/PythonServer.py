from concurrent import futures
import grpc
import Greeter_pb2 as Action
import Greeter_pb2_grpc as Service
import calculator_pb2_grpc as CalculatorService
import calculator_pb2 as CalculatorAction
import rubrica_pb2 as RubricaAction
import rubrica_pb2_grpc as RubricaService


rubrica = {}


class Greeter(Service.GreeterServicer):
    def SayHello(self, request, context):
        return Action.HelloReply(message="Sono gRPC su python, Ciao " + request.name)


class Calculator(CalculatorService.CalculatorServicer):
    def Add(self, request, context):
        return CalculatorAction.OperationResponse(res=request.opA+request.opB)

    def Subtract(self, request, context):
        return CalculatorAction.OperationResponse(res=request.opA-request.opB)

    def Multiply(self, request, context):
        return CalculatorAction.OperationResponse(res=request.opA*request.opB)

    def Divide(self, request, context):
        return CalculatorAction.OperationResponse(res=request.opA/request.opB)


class Rubrica(RubricaService.RubricaServicer):
    def AddContact(self, request, context):
        rubrica[request.nome + request.cognome] = RubricaAction.Person(nome=request.nome, cognome=request.cognome, telefono=request.telefono)
        return RubricaAction.Result(res=True)

    def GetContact(self, request, context):
        return RubricaAction.Person(nome=request.nome, cognome=request.cognome, telefono=rubrica[request.nome + request.cognome])

    def GetAll(self, request, context):
        return RubricaAction.ContactList(contatti=rubrica.values())



port = "50051"
server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
Service.add_GreeterServicer_to_server(Greeter(), server)
CalculatorService.add_CalculatorServicer_to_server(Calculator(), server)
RubricaService.add_RubricaServicer_to_server(Rubrica(), server)
server.add_insecure_port("localhost:"+port)
server.start()
print(f"Listening on port {port}")
server.wait_for_termination()