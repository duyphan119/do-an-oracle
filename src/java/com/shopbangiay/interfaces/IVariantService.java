package com.shopbangiay.interfaces;

import com.shopbangiay.models.Variant;
import java.util.List;

public interface IVariantService {
    List<Variant> findAllVariants();
}
