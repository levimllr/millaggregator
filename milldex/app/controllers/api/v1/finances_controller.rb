class Api::V1::FinancesController < ApplicationController
  @@ynab_url = 'https://api.youneedabudget.com/v1'
  @@ynab_pat = ''

  def index
    budget_resp = RestClient.get(@@ynab_url + '/budgets', {:Authorization => 'Bearer ' + @@ynab_pat})
    budget_body = JSON.parse(budget_resp.body)

    render json: budget_body
  end
end
