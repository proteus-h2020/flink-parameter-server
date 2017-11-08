package hu.sztaki.ilab.ps.lasso

import hu.sztaki.ilab.ps.lasso.LassoParameterServer.{LassoModel, LassoParam}

class LassoModelBuilder (val baseModel: LassoModel) extends ModelBuilder[LassoParam, LassoModel] {
  def addParams(param1: LassoParam, param2: LassoParam): LassoParam =
    (param1._1 + param2._1, param1._2 + param2._2)

  override def buildModel(params: Iterable[(Int, LassoParam)],
                          featureCount: Int): LassoModel = {
    params.map(x => x._2).reduce(addParams(_, _))
  }

  def add(id: Int, incModel: LassoModel): LassoModel = {
    addParams(baseModel, incModel)
  }
}

/**
  * Generic model builder for Lasso cases.
  *
  * @tparam Param
  * Type of Parameter Server parameter.
  * @tparam Model
  * Type of the model.
  */
protected trait ModelBuilder[Param, Model] extends Serializable {

  /**
    * Creates a model out of single parameters.
    *
    * @param params
    * Parameters.
    * @param featureCount
    * Number of features.
    * @return
    * Model.
    */
  def buildModel(params: Iterable[(Int, Param)], featureCount: Int): Model

}