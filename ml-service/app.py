from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
# from sklearn.linear_model import LinearRegression # Exemplo de import de modelo

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'ML Service is running!'

@app.route('/predict_cash_flow', methods=['POST'])
def predict_cash_flow():
    data = request.get_json()
    # Exemplo de processamento:
    # Aqui você carregaria seu modelo de ML e faria a previsão
    # Por enquanto, vamos simular uma resposta

    # Suponha que 'data' contenha um histórico de transações
    # e você queira prever o fluxo de caixa para os próximos meses.

    # Exemplo de dados de entrada esperados:
    # {
    #   "transactions": [
    #     {"date": "2023-01-01", "amount": 100.0},
    #     {"date": "2023-01-15", "amount": -50.0},
    #     ...
    #   ],
    #   "months_to_predict": 3
    # }

    if not data or 'transactions' not in data or 'months_to_predict' not in data:
        return jsonify({"error": "Invalid input data"}), 400

    transactions = pd.DataFrame(data['transactions'])
    months_to_predict = data['months_to_predict']

    # Lógica de previsão simulada
    # Em um cenário real, você treinaria um modelo com dados históricos
    # e usaria-o aqui para fazer previsões.

    # Por exemplo, uma previsão simples baseada na média mensal
    if not transactions.empty:
        transactions['date'] = pd.to_datetime(transactions['date'])
        transactions['month'] = transactions['date'].dt.to_period('M')
        monthly_flow = transactions.groupby('month')['amount'].sum()

        if not monthly_flow.empty:
            average_monthly_flow = monthly_flow.mean()
            predictions = [average_monthly_flow for _ in range(months_to_predict)]
        else:
            predictions = [0.0 for _ in range(months_to_predict)] # Sem dados, sem fluxo
    else:
        predictions = [0.0 for _ in range(months_to_predict)] # Sem transações, sem fluxo

    return jsonify({"predictions": predictions})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
